FROM node:14

WORKDIR /app

COPY app.js .

RUN npm init -y && npm install express

EXPOSE 8082 

CMD ["node", "app.js"]