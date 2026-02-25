import { createContext, useState, useEffect } from "react";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {

  const [user, setUser] = useState(null);

  // udit 

  useEffect(() => {
    const token = localStorage.getItem("token");
    const name = localStorage.getItem("name");
    const role = localStorage.getItem("role");

    if (token) {
      setUser({ token, name, role });
    }
  }, []);

  const login = (data) => {
    localStorage.setItem("token", data.token);
    localStorage.setItem("name", data.teacherName);
    localStorage.setItem("role", data.role);

    setUser({
      token: data.token,
      name: data.teacherName,
      role: data.role
    });
  };

  const logout = () => {
    localStorage.clear();
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};