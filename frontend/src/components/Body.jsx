import Container from "react-bootstrap/esm/Container";
import Row from "react-bootstrap/esm/Row";

const Body = ({ children }) => {
  return (
    <Container id="main-container">
      <Row>{children}</Row>
    </Container>
  );
};

export default Body;
