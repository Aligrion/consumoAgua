import { STORES, getStore } from "../store/store.js";

class ConsumoAgua {
  //Construtor da classe ConsumoAgua
  constructor(id, timestamp, quantidade) {
    this.id = id;
    this.timestamp = timestamp;
    this.quantidade = quantidade;
  }

  static async salvar(quantidade) {
    //Pega o timestamp atual
    const timestamp = new Date().getTime();
    //Pega a store de registro de consumo de água
    const store = await getStore(STORES.REGISTRO_CONSUMO_AGUA);
    //Faz uma requisição de adição de um novo registro de consumo de água
    const saveRequest = store.add({
      quantidade,
      timestamp,
    });

    //Retorna uma promise que resolve com o novo registro de consumo de água
    return new Promise((resolve, reject) => {
      //Se a requisição for bem sucedida, resolve a promise com o novo registro de consumo de água
      saveRequest.onsuccess = () => {
        const id = request.result;
        resolve(new ConsumoAgua(id, timestamp, quantidade));
      };
      //Se a requisição falhar, rejeita a promise com o erro
      saveRequest.onerror = () => {
        reject(request.error);
      };
    });
  }
}

//Exporta a classe ConsumoAgua
export default ConsumoAgua;
