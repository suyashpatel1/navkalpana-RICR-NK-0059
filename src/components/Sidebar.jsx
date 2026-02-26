
import { NavLink } from "react-router-dom";



export default function Sidebar() {
  return (
    <div className="sidebar">
      <h3 className="sidebar-title">RICR Dashboard</h3>

      <nav className="sidebar-menu">

        <NavLink to="/" end>
          📊 Dashboard
        </NavLink>

        <NavLink to="/students">
          🎓 Student Management
        </NavLink>

        <NavLink to="/batches">
          📚 Batch Management
        </NavLink>
          <NavLink to="/Asseement">
          Assesment management
        </NavLink>
          <NavLink to="/Attendence">
          📚 Attendence Management
        </NavLink>

      </nav>
    </div>
  );
}