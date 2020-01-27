const config = {
    app: {
        port: parseInt(process.env.APP_PORT) || 3000
    },
    db: {
        host: process.env.DB_HOST || 'localhost',
        port: parseInt(process.env.DB_PORT) || 27017,
        dbName: process.env.DB_NAME || 'db',
        user: process.env.DB_USER || 'dev',
        pass: process.env.DB_PASS || 'DevPass'
    },   
    processing: {
        url: '35.237.68.44:3001'
    }
};


module.exports = config;