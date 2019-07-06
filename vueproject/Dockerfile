## From Template
#FROM node:lts-alpine
## install simple http server for serving static content
#RUN npm install -g http-server
## make the 'app' fozder the current working directory
#WORKDIR /app
## copy both 'package.json' and 'package-lock.json' (if available)
#COPY package*.json ./
## install project dependencies
#RUN npm install
## これ必要ないかなー。とりあえず置いとく。
##RUN apk update && \
##    npm install -g npm && \
##    npm install -g @vue/cli
## copy project files and folders to the current working directory (i.e. 'app' folder)
#COPY . .
## build app for production with minification
#RUN npm run build
#EXPOSE 8080
#CMD [ "http-server", "dist" ]

# build stage
FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# production stage if we use nginx
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

# then we have to build the Docker image
# `$docker build -t vuejs-cookbook/dockerize-vuejs-app .`

# finaly Run Vue app
# `$docker run -it -p 8080:80 --rm --name dockerize-vuejs-app-1 vuejs-cookbook/dockerize-vuejs-app`
