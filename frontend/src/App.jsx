import "./App.css";
import { useService } from "./api/Service";
import { useEffect, useState } from "react";
import Investment from "./components/Investment";
import Skeleton from "./components/Skeleton";

function App() {
  const {
    getShare,
    getStocksLastXDays,
    getAllData,
    getAllUsers,
    getUser,
    buyShares,
    sellShares,
  } = useService();
  const [stocks, setStocks] = useState();
  const [multipleStocks, setMultipleStocks] = useState();

  useEffect(() => {
    async function getStocksData() {
      try {
        const stocks = await getAllData();
        setStocks(stocks);
      } catch (error) {
        console.log(error.response.data.errors);
      }
    }
    if (!stocks) {
      getStocksData();
    }
  }, [getAllData, stocks]);

  // useEffect(() => {
  //   async function getMultipleStocks() {
  //     try {
  //       const multipleStocks = await getStocksLastXDays(7);
  //       setMultipleStocks(multipleStocks);
  //     } catch (error) {
  //       console.log(error.response.data.errors);
  //     }
  //   }
  //   if (!multipleStocks) {
  //     getMultipleStocks();
  //   }
  // }, [getStocksLastXDays, multipleStocks]);

  console.log(stocks);
  // console.log(multipleStocks);

  return (
    <>
      <Skeleton />
    </>
  );
}

export default App;
