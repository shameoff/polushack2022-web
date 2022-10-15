FROM node:18-alpine
WORKDIR /app
COPY package.json package.json
COPY package-lock.json package-lock.json
RUN npm install
COPY . .
# RUN useradd -ms /bin/bash dockeruser
# RUN chmod 777 /code --recursive
# USER dockeruser
CMD ["npm", "start"]
