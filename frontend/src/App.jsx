import './App.css'
import Performance from './components/Performance'

function App() {
  return (
    <>
      {/* TODO: create homepage */}
      <div id = "top">
          <p style={{textAlign: "left"}}>Available Balance </p>
          <p style={{textAlign: "left", display: "inline-block"}}><b>$40000</b></p>
          <button class="button" onclick="location.href='default.html';" style={{float: "right"}}>Manage Funds</button><br></br>
          {/* <p style="display:inline-block;*/}
      </div>
      <div id = "mainContainer">  
        <div id = "container">
          <div id="left-container">
            left container
            <img src = "../line-graph.png"></img>
          </div>
        </div>
        
      </div>
    
      

    </>
  )
}

export default App
