const path = require('path');

const { Storage } = require('@google-cloud/storage');

const GOOGLE_CLOUD_PROJECT_ID = 'bibliotecavirtual-266119'; // Replace with your project ID
const GOOGLE_CLOUD_KEYFILE = path.normalize(path.join(__dirname, "../config/bibliotecavirtual-266119-099f0ef4e7fb.json")); // Replace with the path to the downloaded private key

const storage = new Storage({
  projectId: GOOGLE_CLOUD_PROJECT_ID,
  keyFilename: GOOGLE_CLOUD_KEYFILE,
});

/**
   * Get public URL of a file. The file must have public access
   * @param {string} bucketName
   * @param {string} fileName
   * @return {string}
   */
module.exports = {
  getPublicUrl: (bucketName, fileName) => `https://storage.googleapis.com/${bucketName}/${fileName}`,
  storage: storage
};