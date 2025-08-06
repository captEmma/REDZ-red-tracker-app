const Performance = ({title, list}) => {

    return (
        <>
            {title}
            {list.map((item) => {
                return (
                    <div>{item}</div>
                );
            })}
        </>
    )
};

export default Performance;