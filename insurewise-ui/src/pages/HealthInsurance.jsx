import React, { useState } from "react";
import axios from "axios";

function HealthInsurance() {
  const [formData, setFormData] = useState({
    policyHolderName: "",
    city: "",
    buildingValue: "",
    contentValue: "",
    carpetArea: "",
    buildingType: "",
    earthquakeCover: false,
    floodCover: false,
  });

  const [responseData, setResponseData] = useState(null);
  const [error, setError] = useState("");

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/api/homepolicy/calculatePremium",
        formData
      );
      setResponseData(response.data);
      setError("");
    } catch (err) {
      setError("Something went wrong. Check server or inputs.");
      setResponseData(null);
    }
  };

  return (
    <div
      style={{
        maxWidth: 500,
        margin: "2rem auto",
        padding: "2rem",
        boxShadow: "0 0 10px rgba(0,0,0,0.1)",
        borderRadius: 8,
        fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
        backgroundColor: "#fff",
      }}
    >
      <h1 style={{ textAlign: "center", marginBottom: "1.5rem", color: "#333" }}>
        Home Insurance Premium Calculator
      </h1>

      <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", gap: "1rem" }}>
        {/* Text Inputs */}
        <label style={{ display: "flex", flexDirection: "column", fontWeight: "600", color: "#555" }}>
          Policy Holder Name
          <input
            name="policyHolderName"
            placeholder="Enter full name"
            value={formData.policyHolderName}
            onChange={handleChange}
            required
            style={inputStyle}
          />
        </label>

        <label style={{ display: "flex", flexDirection: "column", fontWeight: "600", color: "#555" }}>
          City
          <input
            name="city"
            placeholder="Enter city"
            value={formData.city}
            onChange={handleChange}
            required
            style={inputStyle}
          />
        </label>

        {/* Number Inputs */}
        <label style={labelWithInput}>
          Building Value (₹)
          <input
            name="buildingValue"
            type="number"
            placeholder="e.g. 5000000"
            value={formData.buildingValue}
            onChange={handleChange}
            required
            style={inputStyle}
            min={0}
          />
        </label>

        <label style={labelWithInput}>
          Content Value (₹)
          <input
            name="contentValue"
            type="number"
            placeholder="e.g. 1000000"
            value={formData.contentValue}
            onChange={handleChange}
            required
            style={inputStyle}
            min={0}
          />
        </label>

        <label style={labelWithInput}>
          Carpet Area (sq.ft)
          <input
            name="carpetArea"
            type="number"
            placeholder="e.g. 1200"
            value={formData.carpetArea}
            onChange={handleChange}
            required
            style={inputStyle}
            min={0}
          />
        </label>

        <label style={{ display: "flex", flexDirection: "column", fontWeight: "600", color: "#555" }}>
          Building Type
          <select
            name="buildingType"
            value={formData.buildingType}
            onChange={handleChange}
            required
            style={{ ...inputStyle, cursor: "pointer" }}
          >
            <option value="" disabled>
              Select building type
            </option>
            <option value="Apartment">Apartment</option>
            <option value="Independent House">Independent House</option>
            <option value="Villa">Villa</option>
            <option value="Other">Other</option>
          </select>
        </label>

        {/* Checkbox group */}
        <div style={{ display: "flex", justifyContent: "space-between", marginTop: "0.5rem" }}>
          <label style={checkboxLabel}>
            <input
              type="checkbox"
              name="earthquakeCover"
              checked={formData.earthquakeCover}
              onChange={handleChange}
              style={{ marginRight: 8 }}
            />
            Earthquake Cover
          </label>

          <label style={checkboxLabel}>
            <input
              type="checkbox"
              name="floodCover"
              checked={formData.floodCover}
              onChange={handleChange}
              style={{ marginRight: 8 }}
            />
            Flood Cover
          </label>
        </div>

        <button
          type="submit"
          style={{
            marginTop: "1.5rem",
            padding: "0.75rem",
            fontWeight: "700",
            fontSize: "1rem",
            color: "#fff",
            backgroundColor: "#007bff",
            border: "none",
            borderRadius: 4,
            cursor: "pointer",
            transition: "background-color 0.3s ease",
          }}
          onMouseOver={(e) => (e.currentTarget.style.backgroundColor = "#0056b3")}
          onMouseOut={(e) => (e.currentTarget.style.backgroundColor = "#007bff")}
        >
          Calculate Premium
        </button>
      </form>

      {responseData && (
        <div
          style={{
            marginTop: "2rem",
            padding: "1rem",
            backgroundColor: "#e6f7ff",
            border: "1px solid #91d5ff",
            borderRadius: 6,
            color: "#0050b3",
          }}
        >
          <h2 style={{ marginBottom: "0.5rem" }}>Premium Details</h2>
          <p>
            <strong>Policy ID:</strong> {responseData.policyId}
          </p>
          <p>
            <strong>Holder:</strong> {responseData.policyHolderName}
          </p>
          <p>
            <strong>Risk:</strong> {responseData.locationRisk}
          </p>
          <p>
            <strong>Premium:</strong> ₹{responseData.premium}
          </p>
          <p>
            <strong>Start Date:</strong> {responseData.startDate}
          </p>
          <p>
            <strong>End Date:</strong> {responseData.endDate}
          </p>
        </div>
      )}

      {error && (
        <p style={{ color: "red", marginTop: "1rem", fontWeight: "600" }}>{error}</p>
      )}
    </div>
  );
}

const inputStyle = {
  padding: "8px 12px",
  borderRadius: 4,
  border: "1px solid #ccc",
  fontSize: 16,
  marginTop: 6,
};

const labelWithInput = {
  display: "flex",
  flexDirection: "column",
  fontWeight: "600",
  color: "#555",
};

const checkboxLabel = {
  display: "flex",
  alignItems: "center",
  fontWeight: "600",
  color: "#555",
  cursor: "pointer",
};

export default HealthInsurance;
