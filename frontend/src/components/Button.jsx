const Button = ({ title, ...rest }) => {
  return (
    <div className="center-content">
      <button className="show-button" {...rest}>
        {title}
      </button>
    </div>
  );
};

export default Button;
