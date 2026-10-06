const http = require('http');

const server = http.createServer(async (req, res) => {
  const db = (await pm.state.get('vehicles')) || {};

  const rawUrl = req.url; // e.g. "/vehicles" or "/vehicles/abc?brand=Ford"
  const [pathPart, queryPart] = rawUrl.split('?');
  const pathParts = pathPart.replace(/^\//, '').split('/');
  // pathParts[0] = 'vehicles', pathParts[1] = id (if present)
  const method = req.method.toUpperCase();
  const vehicleId = pathParts[1] || null;

  // Parse query params manually
  const searchParams = new URLSearchParams(queryPart || '');

  function generateId() {
    return Math.random().toString(36).substr(2, 9);
  }

  function saveDb(data) {
    pm.state.set('vehicles', data);
  }

  function getBody() {
    return new Promise((resolve) => {
      let body = '';
      req.on('data', chunk => { body += chunk; });
      req.on('end', () => {
        try { resolve(JSON.parse(body || '{}')); }
        catch (e) { resolve({}); }
      });
    });
  }

  // POST /vehicles
  // @endpoint POST /vehicles
  if (method === 'POST' && !vehicleId) {
    const body = await getBody();
    const required = ['vin', 'make', 'model', 'year', 'miles'];
    const missing = required.filter(f => body[f] === undefined || body[f] === null || body[f] === '');
    if (missing.length > 0) {
      res.writeHead(400, { 'Content-Type': 'application/json' });
      res.end(JSON.stringify({ error: 'Missing required fields', fields: missing }));
      return;
    }
    // Check for duplicate VIN
    const duplicate = Object.values(db).find(v => v.vin === body.vin);
    if (duplicate) {
      res.writeHead(409, { 'Content-Type': 'application/json' });
      res.end(JSON.stringify({ error: 'A vehicle with this VIN already exists', vin: body.vin }));
      return;
    }
    const id = generateId();
    const vehicle = { id, nickName: body.nickName || null, vin: body.vin, make: body.make, model: body.model, year: body.year, miles: body.miles };
    db[id] = vehicle;
    saveDb(db);
    res.writeHead(201, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify(vehicle));
    return;
  }

  // GET /vehicles
  // @endpoint GET /vehicles
  if (method === 'GET' && !vehicleId) {
    const brand = searchParams.get('brand');
    let vehicles = Object.values(db);
    if (brand) {
      vehicles = vehicles.filter(v => v.make && v.make.toLowerCase() === brand.toLowerCase());
    }
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify(vehicles));
    return;
  }

  // GET /vehicles/:id
  // @endpoint GET /vehicles/:id
  if (method === 'GET' && vehicleId) {
    const vehicle = db[vehicleId];
    if (!vehicle) {
      res.writeHead(404, { 'Content-Type': 'application/json' });
      res.end(JSON.stringify({ error: 'Vehicle not found', id: vehicleId }));
      return;
    }
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify(vehicle));
    return;
  }

  // PATCH /vehicles/:id
  // @endpoint PATCH /vehicles/:id
  if (method === 'PATCH' && vehicleId) {
    const vehicle = db[vehicleId];
    if (!vehicle) {
      res.writeHead(404, { 'Content-Type': 'application/json' });
      res.end(JSON.stringify({ error: 'Vehicle not found', id: vehicleId }));
      return;
    }
    const body = await getBody();
    const updatable = ['nickName', 'vin', 'make', 'model', 'year', 'miles'];
    updatable.forEach(field => {
      if (body[field] !== undefined) vehicle[field] = body[field];
    });
    db[vehicleId] = vehicle;
    saveDb(db);
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify(vehicle));
    return;
  }

  // DELETE /vehicles/:id
  // @endpoint DELETE /vehicles/:id
  if (method === 'DELETE' && vehicleId) {
    if (!db[vehicleId]) {
      res.writeHead(404, { 'Content-Type': 'application/json' });
      res.end(JSON.stringify({ error: 'Vehicle not found', id: vehicleId }));
      return;
    }
    delete db[vehicleId];
    saveDb(db);
    res.writeHead(204);
    res.end('');
    return;
  }

  // Fallback
  res.writeHead(404, { 'Content-Type': 'application/json' });
  res.end(JSON.stringify({ error: 'Route not found' }));
});

server.listen(process.env.PORT || 4501);