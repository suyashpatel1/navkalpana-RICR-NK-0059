import { useEffect, useState } from "react";
import API from "../api/Axoisinstance";

//Batches 
export default function Batches() {

  const [batches, setBatches] = useState([]);
  const [form, setForm] = useState({
    name: "",
    type: "",
    totalStudents: "",
    progress: "",
    status: "ONGOING",
    startDate: "",
    endDate: ""
  });
  const [editId, setEditId] = useState(null);

  useEffect(() => {
    fetchBatches();
  }, []);

  const fetchBatches = async () => {
    const res = await API.get("/batches");
    setBatches(res.data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (editId) {
      await API.put(`/batches/${editId}`, form);
      setEditId(null);
    } else {
      await API.post("/batches", form);
    }

    setForm({
      name: "",
      type: "",
      totalStudents: "",
      progress: "",
      status: "ONGOING",
      startDate: "",
      endDate: ""
    });

    fetchBatches();
  };

  const handleEdit = (batch) => {
    setEditId(batch.id);
    setForm(batch);
  };

  const handleDelete = async (id) => {
    await API.delete(`/batches/${id}`);
    fetchBatches();
  };

  const filterByStatus = async (status) => {
    if (status === "ALL") {
      fetchBatches();
    } else {
      const res = await API.get(`/batches/status/${status}`);
      setBatches(res.data);
    }
  };

  return (
    <div className="page-card">
      <h2>📚 Batch Management</h2>

      {/* Filter Buttons */}
      <div className="filter-buttons">
        <button onClick={() => filterByStatus("ALL")}>All</button>
        <button onClick={() => filterByStatus("ONGOING")}>Ongoing</button>
        <button onClick={() => filterByStatus("COMPLETED")}>Completed</button>
        <button onClick={() => filterByStatus("UPCOMING")}>Upcoming</button>
      </div>

      {/* Form */}
      <form onSubmit={handleSubmit} className="batch-form">

        <input
          type="text"
          placeholder="Batch Name"
          value={form.name}
          onChange={(e) => setForm({ ...form, name: e.target.value })}
          required
        />

        <input
          type="text"
          placeholder="Type (Online/Offline)"
          value={form.type}
          onChange={(e) => setForm({ ...form, type: e.target.value })}
          required
        />

        <input
          type="number"
          placeholder="Total Students"
          value={form.totalStudents}
          onChange={(e) => setForm({ ...form, totalStudents: e.target.value })}
          required
        />

        <input
          type="number"
          placeholder="Progress %"
          value={form.progress}
          onChange={(e) => setForm({ ...form, progress: e.target.value })}
          required
        />

        <select
          value={form.status}
          onChange={(e) => setForm({ ...form, status: e.target.value })}
        >
          <option value="ONGOING">ONGOING</option>
          <option value="COMPLETED">COMPLETED</option>
          <option value="UPCOMING">UPCOMING</option>
        </select>

        <input
          type="date"
          value={form.startDate}
          onChange={(e) => setForm({ ...form, startDate: e.target.value })}
        />

        <input
          type="date"
          value={form.endDate}
          onChange={(e) => setForm({ ...form, endDate: e.target.value })}
        />

        <button type="submit">
          {editId ? "Update Batch" : "Add Batch"}
        </button>
      </form>

      {/* Table */}
      <table className="data-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Type</th>
            <th>Total</th>
            <th>Progress</th>
            <th>Status</th>
            <th>Start</th>
            <th>End</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {batches.map((batch) => (
            <tr key={batch.id}>
              <td>{batch.id}</td>
              <td>{batch.name}</td>
              <td>{batch.type}</td>
              <td>{batch.totalStudents}</td>
              <td>{batch.progress}%</td>
              <td>{batch.status}</td>
              <td>{batch.startDate}</td>
              <td>{batch.endDate}</td>
              <td>
                <button onClick={() => handleEdit(batch)}>Edit</button>
                <button onClick={() => handleDelete(batch.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}