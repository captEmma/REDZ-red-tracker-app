import Container from "react-bootstrap/esm/Container.js";
import "./Metric.css";
import MetricItem from "./MetricItem.jsx";
import Row from "react-bootstrap/esm/Row.js";
import { useService } from "../api/Service";
import { useEffect, useState } from "react";

const Metric = () => {
  const { getGainers, loadPerformance } = useService();
  const [stocks, setStocks] = useState();

  useEffect(() => {
    async function getStocksData() {
      try {
        const lp = await loadPerformance();
        const stocks = await getGainers();
        setStocks(stocks);
      } catch (error) {
        console.log(error.response.data.errors);
      }
    }
    if (!stocks) {
      getStocksData();
    }
  }, [getGainers, stocks]);

  return (
    <>
      {stocks && (
        <div>
          <Container>
            <Row>
              {stocks.map((data) => (
                <MetricItem
                  key={data.symbol}
                  stockId={data.symbol}
                  name={data.companyName}
                  price={data.purchasePrice}
                />
              ))}
            </Row>
            <Row>
              <button className="show-all">Show All</button>
            </Row>
          </Container>
        </div>
      )}{" "}
    </>
  );
};

export default Metric;
