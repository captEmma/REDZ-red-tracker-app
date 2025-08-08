import Container from "react-bootstrap/esm/Container.js";
import "./Metric.css";
import MetricItem from "./MetricItem.jsx";
import Row from "react-bootstrap/esm/Row.js";
import { useService } from "../api/Service";
import { useEffect, useState } from "react";
import Button from "./Button";

const Metric = ({ title }) => {
  const { getGainers, loadPerformance } = useService();
  const [stocks, setStocks] = useState();

  useEffect(() => {
    async function getStocksData() {
      try {
        await loadPerformance();
        const stocks = await getGainers();
        setStocks(stocks);
      } catch (error) {
        console.log(error);
      }
    }
    if (!stocks) {
      getStocksData();
    }
  }, [getGainers, loadPerformance, stocks]);

  return (
    <>
      {stocks && (
        <div className="px-0">
          <Container className="component-bg metric">
            <div className="metric-title">{title}</div>
            <Row>
              {stocks.map((data) => (
                <MetricItem key={data.symbol} symbol={data.symbol} name={data.companyName} price={data.purchasePrice} />
              ))}
            </Row>
            <Row>
              <Button title="show all" />
            </Row>
          </Container>
        </div>
      )}
    </>
  );
};

export default Metric;
