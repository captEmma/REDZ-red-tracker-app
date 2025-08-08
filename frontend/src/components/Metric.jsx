import Container from "react-bootstrap/esm/Container.js";
import "./Metric.css";
import MetricItem from "./MetricItem.jsx";
import Row from "react-bootstrap/esm/Row.js";
import { useService } from "../api/Service";
import { useEffect, useState } from "react";
import Button from "./Button";

const Metric = ({ title }) => {
  const { getGainers, loadPerformance, getLosers } = useService();
  const [stocks, setStocks] = useState();
  const [lStocks, setlStocks] = useState();

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

  useEffect(() => {
    async function getLoserData() {
      try {
        await loadPerformance();
        const lStocks = await getLosers();
        setlStocks(lStocks);
      } catch (error) {
        console.log(error.response.data.errors);
      }
    }
    if (!lStocks) {
      getLoserData();
    }
  }, [getLosers, loadPerformance, lStocks]);

  if (title == "Gainers") {
    return (
      <>
        {stocks && (
          <div className="px-0">
            <Container className="component-bg metric">
              <div className="metric-title">{title}</div>
              <Row>
                {stocks.map((data) => (
                  <MetricItem
                    key={data.item.symbol}
                    symbol={data.item.symbol}
                    name={data.item.companyName}
                    performance={Math.round(data.performance * 10000) / 100}
                  />
                ))}
              </Row>
              <Row>
                <Button title="Show all" />
              </Row>
            </Container>
          </div>
        )}
      </>
    );
  } else if (title == "Losers") {
    return (
      <>
        {lStocks && (
          <div className="px-0">
            <Container className="component-bg metric">
              <div className="metric-title">{title}</div>
              <Row>
                {lStocks.map((data) => (
                  <MetricItem
                    key={data.item.symbol}
                    symbol={data.item.symbol}
                    name={data.item.companyName}
                    performance={Math.round(data.performance * 10000) / 100}
                  />
                ))}
              </Row>
              <Row>
                <Button title="Show all" />
              </Row>
            </Container>
          </div>
        )}
      </>
    );
  }
};

export default Metric;
