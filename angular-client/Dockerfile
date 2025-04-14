# Stage 1: Compile and Build Angular codebase
FROM node:18-alpine AS build

# Set the working directory
WORKDIR /usr/local/app

# Copy package.json and package-lock.json to leverage caching
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the source code
COPY ./ ./

# Generate the build of the application
RUN npm run build

# Stage 2: Serve app with Nginx server
FROM nginx:alpine

# Copy the build output to replace the default Nginx contents
COPY --from=build /usr/local/app/dist/angular-client/browser /usr/share/nginx/html

# Copy the Nginx configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 80
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]
