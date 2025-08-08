import Container from "react-bootstrap/esm/Container";
import "./Skeleton.css";
import Row from "react-bootstrap/esm/Row";
import Col from "react-bootstrap/esm/Col";

const Header = () => {
  return (
    <Container fluid style={{ width: "100%", padding: "0px" }}>
      <Row className="mx-0" id="top">
        <Col>
          <div>
            Available Balance: <b>$40000</b>
          </div>
        </Col>
        <Col>
          <button className="funds-button">Manage Funds</button>
        </Col>
      </Row>
    </Container>
  );
};

export default Header;
