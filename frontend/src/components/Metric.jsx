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
        console.log(error.response.data.errors);
      }
    }
    if (!stocks) {
      getStocksData();
    }
  }, [getGainers, loadPerformance, stocks]);

  return (
    <div className="px-0">
      <Container className="component-bg metric">
        <div className="metric-title">{title}</div>
        <Row>
          {stocks.map((data) => (
            <MetricItem key={data.stockId} stockId={data.stockId} name={data.name} percentage={data.percentage} />
          ))}
        </Row>
        <Row>
          <Button />
        </Row>
      </Container>
    </div>
  );
};

export default Metric;
