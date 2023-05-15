const STORES = {
  REGISTRO_CONSUMO_AGUA: "registro_consumo_agua",
};

const DB_NAME = "consumo-agua-bd";

let dbPromise;

const init = (onSuccess, onError) => {
  const dbRequest = window.indexedDB.open(DB_NAME, 1);

  dbRequest.onsuccess = function (event) {
    console.log("Banco de dados inicializado.");
    onSuccess(event.target.result);
  };

  dbRequest.onerror = function (event) {
    onError(event.target);
  };

  dbRequest.onupgradeneeded = function (event) {
    const db = event.target.result;
    db.createObjectStore(STORES.REGISTRO_CONSUMO_AGUA, {
      keyPath: "id",
      autoIncrement: true,
    });
    console.log("Banco de dados criado!");
  };
};

const getDB = () => {
  if (!dbPromise) {
    dbPromise = new Promise((resolve, reject) => {
      init((db) => {
        resolve(db);
      }, reject);
    });
  }

  return dbPromise;
};

const getStore = async (store, mode = "readwrite") => {
  const db = await getDB();
  return db.transaction(store, mode).objectStore(store);
};

export { STORES, getDB, getStore };
