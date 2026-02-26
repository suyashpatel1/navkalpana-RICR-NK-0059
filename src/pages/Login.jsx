import { useState, useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import API from "../api/Axoisinstance";
import { useNavigate, Link } from "react-router-dom";
//LOGIN 
export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    const res = await API.post("/auth/login", { email, password });
    login(res.data);
    navigate("/");
  };

  return (
    <div className="auth-container">
      <form onSubmit={handleLogin} className="auth-card">
        <h2>Login</h2>
        <input 
          type="email" 
          placeholder="Email" 
          onChange={(e) => setEmail(e.target.value)} 
        />
        <input 
          type="password" 
          placeholder="Password" 
          onChange={(e) => setPassword(e.target.value)} 
        />
        <button type="submit">Login</button>

        <p>
          Don't have account? <Link to="/register">Register</Link>
        </p>
      </form>
    </div>
  );
}