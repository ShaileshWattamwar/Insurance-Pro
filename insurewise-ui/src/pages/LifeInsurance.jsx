import React, { useState } from "react";
import axios from "axios";

function LifeInsurance() {
  const [formData, setFormData] = useState({
    customer_name: "",
    customer_age: "",
    gender: "",
    smoker: false,
    occupation: "",
    healthIssues: false,
    covidaffected: false,
    sum_assured: "",
    policy_term: "",
    riders: [],
  });

  const [responseData, setResponseData] = useState(null);
  const [error, setError] = useState("");
  const [buttonPressed, setButtonPressed] = useState(false);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    if (name === "riders") {
      const updatedRiders = formData.riders.includes(value)
        ? formData.riders.filter((r) => r !== value)
        : [...formData.riders, value];
      setFormData((prev) => ({ ...prev, riders: updatedRiders }));
    } else {
      setFormData((prev) => ({
        ...prev,
        [name]: type === "checkbox" ? checked : value,
      }));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/Lifeinsurance/add",
        formData
      );
      setResponseData(response.data);
      setError("");
    } catch (err) {
      setError("Something went wrong. Please try again later.");
      setResponseData(null);
    }
  };

  return (
    <div style={page}>
      {/* Left - Form Section */}
      <div style={leftPanel}>
        <h1 style={title}>Life Insurance Premium Calculator</h1>

        <form onSubmit={handleSubmit} style={form}>
          <FloatingInput
            label="Customer Name"
            name="customer_name"
            value={formData.customer_name}
            onChange={handleChange}
            required
          />
          <FloatingInput
            label="Age"
            name="customer_age"
            type="number"
            value={formData.customer_age}
            onChange={handleChange}
            required
          />
          <FloatingInput
            label="Gender"
            name="gender"
            value={formData.gender}
            onChange={handleChange}
            required
          />
          <FloatingInput
            label="Occupation"
            name="occupation"
            value={formData.occupation}
            onChange={handleChange}
            required
          />
          <FloatingInput
            label="Sum Assured (₹)"
            name="sum_assured"
            type="number"
            value={formData.sum_assured}
            onChange={handleChange}
            required
          />
          <FloatingInput
            label="Policy Term (Years)"
            name="policy_term"
            type="number"
            value={formData.policy_term}
            onChange={handleChange}
            required
          />

          <div style={togglesWrapper}>
            <ToggleSwitch
              label="Smoker"
              name="smoker"
              checked={formData.smoker}
              onChange={handleChange}
            />
            <ToggleSwitch
              label="Health Issues"
              name="healthIssues"
              checked={formData.healthIssues}
              onChange={handleChange}
            />
            <ToggleSwitch
              label="COVID Affected"
              name="covidaffected"
              checked={formData.covidaffected}
              onChange={handleChange}
            />
          </div>

          <div style={checkboxGroup}>
            <p style={{ fontWeight: 600 }}>Select Riders:</p>
            {['Accidental Death', 'Critical Illness', 'Disability'].map((rider) => (
              <label key={rider} style={checkboxLabel}>
                <input
                  type="checkbox"
                  name="riders"
                  value={rider}
                  checked={formData.riders.includes(rider)}
                  onChange={handleChange}
                />
                {rider}
              </label>
            ))}
          </div>

          <button
            type="submit"
            style={{ ...button, ...(buttonPressed ? buttonActive : {}) }}
            onMouseDown={() => setButtonPressed(true)}
            onMouseUp={() => setButtonPressed(false)}
            onMouseLeave={() => setButtonPressed(false)}
          >
            Calculate Premium
          </button>
        </form>

        {error && <p style={errorStyle}>{error}</p>}
      </div>

      {/* Right - Info and Response */}
      <div style={rightPanel}>
        <h2 style={sectionTitle}>💡 Life Insurance Tips</h2>
        <ul style={list}>
          <li>✔️ Choose appropriate policy term based on your age and needs.</li>
          <li>✔️ Consider riders for added protection.</li>
          <li>✔️ Non-smokers often get lower premiums.</li>
        </ul>

        {responseData && (
          <div style={responseBox}>
            <h2>Premium Details</h2>
            <DetailRow label="Base Premium" value={`₹${responseData.base_premium}`} />
            <DetailRow label="Risk Premium" value={`₹${responseData.risk_premium}`} />
            <DetailRow label="Rider Premium" value={`₹${responseData.rider_premium}`} />
            <DetailRow label="Total Premium" value={`₹${responseData.total_premium}`} />
          </div>
        )}
      </div>
    </div>
  );
}

function FloatingInput({ label, name, type = "text", value, onChange, required }) {
  const [focused, setFocused] = useState(false);
  const shouldFloat = focused || value;

  return (
    <div style={floatingInputWrapper}>
      <input
        style={{
          ...floatingInput,
          borderColor: shouldFloat ? "#2563eb" : "#ccc",
        }}
        type={type}
        name={name}
        value={value}
        onChange={onChange}
        onFocus={() => setFocused(true)}
        onBlur={() => setFocused(false)}
        placeholder=" "
        required={required}
      />
      <label
        style={{
          ...floatingLabel,
          top: shouldFloat ? 4 : "50%",
          fontSize: shouldFloat ? 12 : 16,
          transform: shouldFloat ? "translateY(0)" : "translateY(-50%)",
          color: shouldFloat ? "#2563eb" : "#999",
        }}
      >
        {label}
      </label>
    </div>
  );
}

function ToggleSwitch({ label, name, checked, onChange }) {
  return (
    <label style={toggleLabel}>
      <input
        type="checkbox"
        name={name}
        checked={checked}
        onChange={onChange}
        style={{ display: "none" }}
      />
      <span style={{ ...toggleSlider, ...(checked ? toggleSliderChecked : {}) }}>
        <span
          style={{
            ...knobStyle,
            transform: checked ? "translateX(22px)" : "translateX(0)",
          }}
        />
      </span>
      <span style={toggleText}>{label}</span>
    </label>
  );
}

function DetailRow({ label, value }) {
  return (
    <p style={{ margin: "6px 0", fontSize: 15 }}>
      <strong>{label}:</strong> {value}
    </p>
  );
}

const page = {
  display: "flex",
  padding: "2rem",
  gap: "2rem",
  fontFamily: "'Segoe UI', sans-serif",
};

const leftPanel = {
  flex: 1.3,
  background: "#fff",
  padding: "2rem",
  borderRadius: 16,
  boxShadow: "0 4px 12px rgba(0,0,0,0.1)",
};

const rightPanel = {
  flex: 1,
  background: "#f0f4f8",
  padding: "2rem",
  borderRadius: 16,
  boxShadow: "0 4px 12px rgba(0,0,0,0.05)",
};

const title = {
  fontSize: 24,
  fontWeight: 700,
  color: "#003366",
  marginBottom: 24,
  textAlign: "center",
};

const form = {
  display: "flex",
  flexDirection: "column",
  gap: 20,
};

const floatingInputWrapper = { position: "relative" };
const floatingInput = {
  width: "100%",
  padding: "14px 16px",
  fontSize: 16,
  borderRadius: 10,
  border: "2px solid #ccc",
  outline: "none",
};
const floatingLabel = {
  position: "absolute",
  left: 18,
  top: "50%",
  pointerEvents: "none",
  transition: "all 0.3s ease",
  backgroundColor: "#fff",
  padding: "0 4px",
};

const togglesWrapper = { display: "flex", gap: 24, flexWrap: "wrap" };
const toggleLabel = { display: "flex", alignItems: "center", gap: 12 };
const toggleSlider = {
  width: 48,
  height: 26,
  borderRadius: 13,
  backgroundColor: "#ccc",
  position: "relative",
  transition: "background 0.3s ease",
};
const toggleSliderChecked = { backgroundColor: "#2563eb" };
const knobStyle = {
  position: "absolute",
  top: 2,
  left: 2,
  width: 22,
  height: 22,
  borderRadius: "50%",
  backgroundColor: "#fff",
  transition: "transform 0.3s ease",
};
const toggleText = { fontSize: 15 };

const checkboxGroup = { marginTop: 20 };
const checkboxLabel = { display: "block", marginBottom: 8 };

const button = {
  padding: "14px",
  fontSize: 16,
  borderRadius: 10,
  border: "none",
  backgroundColor: "#007bff",
  color: "#fff",
  fontWeight: 600,
  cursor: "pointer",
  transition: "transform 0.1s ease",
};
const buttonActive = { transform: "scale(0.97)" };

const responseBox = {
  marginTop: 24,
  padding: 20,
  backgroundColor: "#e0f2fe",
  borderRadius: 12,
};

const errorStyle = {
  marginTop: 16,
  color: "#d00000",
  fontWeight: 600,
  textAlign: "center",
};

const sectionTitle = {
  fontSize: 18,
  fontWeight: 700,
  margin: "1rem 0 0.5rem",
  color: "#1e293b",
};

const list = {
  listStyleType: "disc",
  paddingLeft: 20,
  marginBottom: 16,
};

export default LifeInsurance;
