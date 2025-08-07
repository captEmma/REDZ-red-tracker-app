import Container from "react-bootstrap/esm/Container";
import "./Skeleton.css";
import Row from "react-bootstrap/esm/Row";
import Col from "react-bootstrap/esm/Col";
import Metric from "./Metric.jsx";

const Skeleton = () => {
  return (
    <>
      <Container fluid style={{ width: "100%", padding: "0px" }}>
        <Row className=" mx-0" id="top">
          <Col>
            <div>
              Available Balance <b>$40000</b>
            </div>
          </Col>
          <Col>
            <button className="button">Manage Funds</button>
          </Col>
        </Row>
      </Container>

      <Container id="main-container">
        <Row>
          <Col md={3}>
            <div id="left-container">
              <h2>
                <b>Gainers</b>
              </h2>
              <Metric />

              <h2>
                <b>Losers</b>
              </h2>
              <img
                src="https://cdn.sanity.io/images/599r6htc/regionalized/128e6a1b9aba13b48886e276b4c02e5c6c68ec01-1108x1108.png?w=1200&q=70&fit=max&auto=format"
                height="200px"
                width="200px"
              ></img>
            </div>
          </Col>
          <Col></Col>
          <Col md={3}>
            <Row id="right-container"></Row>
            <Row id="b-s-container"></Row>
          </Col>
        </Row>
      </Container>
    </>
  );
};
export default Skeleton;
