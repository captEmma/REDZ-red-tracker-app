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
  const getAllShares = async () => {
    try {
      const response = await httpInstance.get("/allstocks");
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
  const getGainers = async () => {
    try {
      const response = await httpInstance.get("/gainers");
      return response.data;
    } catch (e) {
      console.error("error getting user - " + e);
    }
  };
  const getLosers = async () => {
    try {
      const response = await httpInstance.get("/losers");
      return response.data;
    } catch (e) {
      console.error("error getting user - " + e);
    }
  };

  const loadPerformance = async () => {
    try {
      const response = await httpInstance.get("/loadperformance");
      return response.data;
    } catch (e) {
      console.error("error getting user - " + e);
    }
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
    sellShares,
    buyShares,
    getAllData,
    getAllUsers,
    getUser,
    getNetworth,
    getAllShares,
    getGainers,
    getLosers,
    loadPerformance,
  };
};
