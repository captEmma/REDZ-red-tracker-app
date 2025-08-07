import Container from "react-bootstrap/esm/Container.js";
import "./Metric.css";
import MetricItem from "./MetricItem.jsx";
import Row from "react-bootstrap/esm/Row.js";

const Metric = () => {
  const mockData = [
    {
      stockId: "AAPL",
      name: "Apple Inc",
      percentage: 10,
    },
    {
      stockId: "TSLA",
      name: "Tesla Motors",
      percentage: 2,
    },
    {
      stockId: "AMZN",
      name: "Amazon Inc",
      percentage: 0.5,
    },
  ];
  return (
    <div>
      <Container>
        <Row>
          {mockData.map((data) => (
            <MetricItem
              key={data.stockId}
              stockId={data.stockId}
              name={data.name}
              percentage={data.percentage}
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
