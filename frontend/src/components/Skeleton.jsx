import Container from "react-bootstrap/esm/Container";
import "./Skeleton.css";
import Row from "react-bootstrap/esm/Row";
import Col from "react-bootstrap/esm/Col";
const Skeleton = () => {
  return (
    <>
      <Container fluid style={{ width: "100%" }}>
        <Row id="top">
          <Col>
            {/* <p style={{ textAlign: "left", display: "inline-block" }}>
              Available Balance <b>$40000</b>
            </p> */}
            <div>
              Available Balance <b>$40000</b>
            </div>
          </Col>
          <Col>
            <button
              class="button"
              onclick="location.href='default.html';"
              style={{ float: "right" }}
            >
              Manage Funds
            </button>
          </Col>
        </Row>
      </Container>
      {/* <div id="top">
        <p style={{ textAlign: "left", display: "inline-block" }}>
          Available Balance <b>$40000</b>
        </p>
       
        <button
          class="button"
          onclick="location.href='default.html';"
          style={{ float: "right" }}
        >
          Manage Funds
        </button>
        <br></br>
      </div> */}
      <div id="main-container">
        <div id="left-container">
          <h2>
            <b>Gainers</b> {/*should be white usnure why its black */}
          </h2>
          <br />

          <img
            src="https://cdn.sanity.io/images/599r6htc/regionalized/128e6a1b9aba13b48886e276b4c02e5c6c68ec01-1108x1108.png?w=1200&q=70&fit=max&auto=format"
            height="200px"
            width="200px"
          ></img>
          <br />
          <br />

          <h2>
            <b>Losers</b> {/*should be white usnure why its black */}
          </h2>

          <br />
          <img
            src="https://cdn.sanity.io/images/599r6htc/regionalized/128e6a1b9aba13b48886e276b4c02e5c6c68ec01-1108x1108.png?w=1200&q=70&fit=max&auto=format"
            height="200px"
            width="200px"
          ></img>
        </div>
        <div id="right-container"></div>
        <div id="b-s-container"></div>
      </div>
    </>
  );
};
export default Skeleton;
