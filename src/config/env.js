const config = {
    app: {
        port: parseInt(process.env.APP_PORT) || 3003
    },
    db: {
        host: process.env.DB_HOST || 'biblioteca-virtual-uz2bf.mongodb.net',
        port: parseInt(process.env.DB_PORT) || 27017,
        dbName: process.env.DB_NAME || 'db',
        user: process.env.DB_USER || 'root',
        pass: process.env.DB_PASS || 'root'
    },   
    processing: {
        url: '35.237.68.44:3001'
    }
};


module.exports = config;