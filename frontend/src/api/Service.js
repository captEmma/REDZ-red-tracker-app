import { useHttp } from "./HttpContext";

export const useService = () => {
  const httpInstance = useHttp();

  const getShare = async (symbol) => {
    try {
      const response = await httpInstance.get(`/yahoo/${symbol}`);
      return response.data;
    } catch (e) {
      console.error("error getting data - " + e);
    }
  };
  const getAllData = async () => {
    try {
      const response = await httpInstance.get("/allcompanies");
      return response.data;
    } catch (e) {
      console.error("error getting data - " + e);
    }
  };
  const getUser = async () => {
    try {
      const response = await httpInstance.get("/user");
      return response.data;
    } catch (e) {
      console.error("error getting user - " + e);
    }
  };
  const getAllUsers = async () => {
    try {
      const response = await httpInstance.get("/user/all");
      return response.data;
    } catch (e) {
      console.error("error getting user - " + e);
    }
  };
  const getNetworth = async () => {
    try {
      const response = await httpInstance.get("/user/networth");
      return response.data;
    } catch (e) {
      console.error("error getting user - " + e);
    }
  };

  const getStocksLastXDays = async (period) => {
    const result = await httpInstance.get(
      `/yahoo/TSLA?period=${period}d&interval=1d`
    );
    return result.data;
  };

  const buyShares = async (symbol, cost) => {
    try {
      const response = await httpInstance.put(`/buy/${symbol}/${cost}`);
      return response.data;
    } catch (e) {
      console.error("error getting data - " + e);
    }
  };

  const sellShares = async (symbol, numShares) => {
    try {
      const response = await httpInstance.put(`/sell/${symbol}/${numShares}`);
      return response.data;
    } catch (e) {
      console.error("error getting data - " + e);
    }
  };

  return {
    getShare,
    getStocksLastXDays,
    sellShares,
    buyShares,
    getAllData,
    getAllUsers,
    getUser,
    getNetworth,
  };
};
