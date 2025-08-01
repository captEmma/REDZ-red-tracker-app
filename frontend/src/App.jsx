import './App.css'
import Performance from './components/Performance'

function App() {
  let list = ["stock1", "stock2"];

  return (
    <>
      {/* TODO: create homepage */}
      <Performance title="example1" list={list}></Performance>
    </>
  )
}

export default App
