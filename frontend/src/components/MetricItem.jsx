import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import "./Metric.css";
const MetricItem = ({ stockId, name, percentage }) => {
  return (
    <Row>
      <Col className="metric-style">
        <b>{stockId}</b> | {name}
        {"   "}
        {percentage > 0 ? "+" : "-"}{" "}
        {percentage < 0 ? percentage * -1 : percentage} %
      </Col>
    </Row>
  );
};

export default MetricItem;
