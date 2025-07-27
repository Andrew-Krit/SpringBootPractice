echo "🛠️ Building user-service..."
docker build -t user-service:latest ./user-service

echo "🛠️ Building notification-service..."
docker build -t notification-service:latest ./notification-service

echo "🛠️ Building gateway-service..."
docker build -t gateway-service:latest ./gateway-service

echo "🛠️ Building discovery-service..."
docker build -t discovery-service:latest ./discovery-service

echo "🛠️ Building config-server..."
docker build -t config-server:latest ./config-server

echo "✅ Done!"