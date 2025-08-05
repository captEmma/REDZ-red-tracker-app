import { useHttp } from "./HttpContext";

export const useService = () => {
    const httpInstance = useHttp();

    const getStocks = async () => {
        const result = await httpInstance.get("/stocks/TSLA");
        return result.data;
    };

    const getStocksLastXDays = async (period) => {
        const result = await httpInstance.get(`/stocks/TSLA?period=${period}d&interval=1d`);
        return result.data;
    };

    return { getStocks, getStocksLastXDays };
};
