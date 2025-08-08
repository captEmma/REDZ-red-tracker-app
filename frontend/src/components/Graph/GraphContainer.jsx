import Container from "react-bootstrap/esm/Container";
import LineGraph from "./Line";
import Row from "react-bootstrap/esm/Row";

const GraphContainer = () => {
  return (
    <>
      <Container>
        <Row>
          <div className="graph-title">Overall Portfolio Performance</div>
        </Row>
        <Row className="center-horizontally">
          <LineGraph />
        </Row>
      </Container>
    </>
  );
};

export default GraphContainer;
