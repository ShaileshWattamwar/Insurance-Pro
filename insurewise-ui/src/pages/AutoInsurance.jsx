import React from "react";

function AutoInsurance() {
  return (
    <div style={container}>
      <div style={imageBox}>
        <img
          src="https://img.freepik.com/free-vector/car-insurance-concept-illustration_114360-22795.jpg?semt=ais_hybrid&w=740"
          alt="Auto Insurance"
          style={imageStyle}
        />
      </div>
    </div>
  );
}

const container = {
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  height: "100vh",
  backgroundColor: "#f4f6f8",
  fontFamily: "'Segoe UI', sans-serif",
};

const imageBox = {
  backgroundColor: "#fff",
  padding: "2rem",
  borderRadius: "16px",
  boxShadow: "0 4px 12px rgba(0, 0, 0, 0.1)",
};

const imageStyle = {
  maxWidth: "100%",
  height: "auto",
  borderRadius: "12px",
};

export default AutoInsurance;
