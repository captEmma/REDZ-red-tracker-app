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

  const getUserRecent = async () => {
    try {
      const response = await httpInstance.get("/user/recent");
      return response.data;
    } catch (e) {
      console.error("error getting data - " + e);
    }
  };

  const getUserRecentNAmount = async (amount) => {
    try {
      const response = await httpInstance.get(`/user/recent?n=${amount}`);
      return response.data;
    } catch (e) {
      console.error("error getting data - " + e);
    }
  };

  const getNetworth = async () => {
    try {
      const response = await httpInstance.get("/user/networth");
      return response.data;
    } catch (e) {
      console.error("error getting user data - " + e);
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

  const loadPerformance = async () => {
    try {
      const response = await httpInstance.get("/loadperformance");
      return response.data;
    } catch (e) {
      console.error("error getting user - " + e);
    }
  };

  const getCompanies = async () => {
    try {
      const response = await httpInstance.get("/companies");
      return response.data;
    } catch (e) {
      console.error("error getting data - " + e);
    }
  };

  const getHistory = async () => {
    try {
      const response = await httpInstance.get("/user/history");
      return response.data;
    } catch (e) {
      console.error("error getting data - " + e);
    }
  };

  return {
    getShare,
    sellShares,
    buyShares,
    getAllUsers,
    getUser,
    getNetworth,
    getAllShares,
    getGainers,
    getLosers,
    loadPerformance,
    getUserRecent,
    getCompanies,
    getHistory,
    getUserRecentNAmount,
  };
};
