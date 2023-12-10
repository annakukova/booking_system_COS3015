// Import React and other necessary libraries (if any)
import React from "react";
import "./Form.css";

const url = "http://localhost:8080/bookroom";

function Form(props) {
    
    
      function submitForm(e) {
        e.preventDefault();
        const formData = new FormData(e.target);
        
    
       

        fetch(url, {
          method: "PUT",
          headers: {
            'Content-Type': 'application/json'
          },
          body: formData,
        })
          .then((response) => response.json())
          .then((data) => {
            console.log(data);
            alert("Booking successful");
          })
          .catch((error) => {
            console.error(error);
            alert("Booking failed");
          });
      }

  return (
    <div className="container background-container">
      <div className="row justify-content-center">
        <div className="col-md-8 container-form">
          <h2 className="mb-4">Room Booking System</h2>

          <form id="bookingForm" onSubmit={submitForm}>
            <div className="form-group">
              <label for="buildingSelect">Select Building:</label>
              <select
                name="buildingSelect"
                className="form-control"
                id="buildingSelect"
              >
                <option value="ABF">ABF</option>
                <option value="BAC">BAC</option>
              </select>
            </div>

            <div className="form-group">
              <label for="roomSelect">Select Room:</label>
              <input
                name="roomSelect"
                type="number"
                className="form-control"
                id="roomSelect"
              />
            </div>

            <div className="form-group">
              <label for="datePicker">Select Date:</label>
              <input
                name="datePicker"
                type="date"
                className="form-control"
                id="datePicker"
              />
            </div>

            <div className="form-group">
              <label for="startingTime">Select Start Time:</label>
              <input
                name="startingTime"
                type="time"
                className="form-control"
                id="startingTime"
              />
            </div>

            <div className="form-group">
              <label for="endingTime">Select End Time:</label>
              <input
                name="endingTime"
                type="time"
                className="form-control"
                id="endingTime"
                required
              />
            </div>

            <div className="form-group">
              <label for="personName">Your Name:</label>
              <input
                name="personName"
                type="text"
                className="form-control"
                id="personName"
                placeholder="Enter your name"
              />
            </div>

            <div className="form-group">
              <label for="studentId">Student ID:</label>
              <input
                name="studentId"
                type="text"
                className="form-control"
                id="studentId"
                placeholder="Enter your student ID"
                required
              />
            </div>

            <div className="center-button">
              <button type="submit" className="btn btn-booking">
                Book Room
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Form;
