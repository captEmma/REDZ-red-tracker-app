import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import "./Metric.css";
const MetricItem = ({ symbol, name, performance }) => {
  return (
    <Row>
      {/* <Col className="metric-style">
        <b>{symbol}</b> | {name}
        {"    "}
        {performance > 0 ? "+ " : "- "}{" "}
        {performance < 0 ? performance * -1 : performance}%
      </Col> */}
      <Col className="metric-style">
        <b>{symbol}</b>
      </Col>
      <Col className="metric-style">
        {name}
        {"    "}
      </Col>
      <Col className="metric-style">
        {performance > 0 ? "+ " : "- "}{" "}
        {performance < 0 ? performance * -1 : performance}%{" "}
      </Col>
      <hr className="one" />
    </Row>
  );
};

export default MetricItem;
