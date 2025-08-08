import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import "./Metric.css";
const MetricItem = ({ stockId, name, price }) => {
  return (
    <Row>
      <Col className="metric-style">
        <b>{stockId}</b> | {name}
        {"    "}
        {price > 0 ? "+ $" : "- $"} {price < 0 ? price * -1 : price}
      </Col>
    </Row>
  );
};

export default MetricItem;
