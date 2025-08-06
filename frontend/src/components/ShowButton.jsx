const ShowButton = ({ ...rest }) => {
  return (
    <div className="center-content">
      <button className="show-button" {...rest}>
        Show All
      </button>
    </div>
  );
};

export default ShowButton;
