import "./App.css";
import { useService } from "./api/Service";
import { useEffect, useState } from "react";

function App() {
    const { getStocks, getStocksLastXDays } = useService();
    const [stocks, setStocks] = useState();
    const [multipleStocks, setMultipleStocks] = useState();

    useEffect(() => {
        async function getStocksData() {
            try {
                const stocks = await getStocks();
                setStocks(JSON.parse(stocks.data));
            } catch (error) {
                console.log(error.response.data.errors);
            }
        }
        if (!stocks) {
            getStocksData();
        }
    }, [getStocks, stocks]);

    useEffect(() => {
        async function getMultipleStocks() {
            try {
                const multipleStocks = await getStocksLastXDays(7);
                setMultipleStocks(JSON.parse(multipleStocks.data));
            } catch (error) {
                console.log(error.response.data.errors);
            }
        }
        if (!multipleStocks) {
            getMultipleStocks();
        }
    }, [getStocksLastXDays, multipleStocks]);

    console.log(stocks);
    console.log(multipleStocks);

    return <></>;
}

export default App;
