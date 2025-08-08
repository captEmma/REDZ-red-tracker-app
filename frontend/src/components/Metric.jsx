import Container from "react-bootstrap/esm/Container.js";
import "./Metric.css";
import MetricItem from "./MetricItem.jsx";
import Row from "react-bootstrap/esm/Row.js";
import { useService } from "./api/Service";
import { useEffect, useState } from "react";

const Metric = () => {
  // const mockData = [
  //   {
  //     stockId: "AAPL",
  //     name: "Apple Inc",
  //     percentage: 10,
  //   },
  //   {
  //     stockId: "TSLA",
  //     name: "Tesla Motors",
  //     percentage: 2,
  //   },
  //   {
  //     stockId: "AMZN",
  //     name: "Amazon Inc",
  //     percentage: 0.5,
  //   },
  // ];
  const { getShare } = useService();
  const [stocks, setStocks] = useState();

  useEffect(() => {
    async function getStocksData() {
      try {
        const stocks = await getShare("tsla");
        setStocks(stocks);
      } catch (error) {
        console.log(error.response.data.errors);
      }
    }
    if (!stocks) {
      getStocksData();
    }
  }, [getShare, stocks]);

  return (
    <div>
      <Container>
        <Row>
          {/* {mockData.map((data) => (
            <MetricItem
              key={data.stockId}
              stockId={data.stockId}
              name={data.name}
              percentage={data.percentage}
            />
          ))} */}
          {stocks.map((data) => (
            <MetricItem
              key={data.symbol}
              stockId={data.symbol}
              name={data.name}
              percentage={data.currentPrice}
            />
          ))}
        </Row>
        <Row>
          <button
            className="show-all"
            //   onclick="location.href='default.html';"
          >
            Show All
          </button>
        </Row>
      </Container>
    </div>
  );
};

export default Metric;
