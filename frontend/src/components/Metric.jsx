import Container from "react-bootstrap/esm/Container.js";
import "./Metric.css";
import MetricItem from "./MetricItem.jsx";
import Row from "react-bootstrap/esm/Row.js";
import Button from "./Button";

const Metric = ({ title }) => {
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
    <div className="px-0">
      <Container className="component-bg metric">
        <div className="metric-title">{title}</div>
        <Row>
          {mockData.map((data) => (
            <MetricItem key={data.stockId} stockId={data.stockId} name={data.name} percentage={data.percentage} />
          ))}
        </Row>
        <Row>
          <Button title="Show all" />
        </Row>
      </Container>
    </div>
  );
};

export default Metric;
