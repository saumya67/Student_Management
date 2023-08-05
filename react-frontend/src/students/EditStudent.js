import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';

export default function EditStudent() {

    let navigate=useNavigate();
    const{id}=useParams()


    const [student, setStudent]=useState({
        name:"",
        username:"",
        email:"",
    });

    const{name,username,email}=student;

    const onInputChange=(e)=>{
        setStudent({...student,[e.target.name]:e.target.value})

    };

    useEffect(()=>{
        loadStudent();

    }, []);

    const onSubmit=async(e)=>{
        e.preventDefault();
        await axios.put(`http://localhost:8080/student/${id}`, student)
        navigate("/");


    };
    const loadStudent =async ()=>{
        const result=await axios.get(`http://localhost:8080/student/${id}`)
        setStudent(result.data)
    }

  return (
    <div className="container">
        <div className="row">
            <div className="col-md-6 offset-md-3 borer rounded p-4 mt-2 shadow">
                <h2 className="text-center m-4">Edit Student</h2>
                <form onSubmit={(e)=>onSubmit(e)}>
                <div className="mb-3">
                    <label htmlFor="Name" className="form-label">
                        Name
                    </label>
                    <input
                    type={"text"}
                    className="form-control"
                    placeholder="Enter your name"
                    name="name" 
                    value={name}
                    onChange={(e)=>onInputChange(e)}
                    />
                    
                </div>
                <div className="mb-3">
                    <label htmlFor="username" className="form-label">
                        Username
                    </label>
                    <input
                    type={"text"}
                    className="form-control"
                    placeholder="Enter your username"
                    name="username" 
                    value={username}
                    onChange={(e)=>onInputChange(e)}
                    />
                    
                </div>
                <div className="mb-3">
                    <label htmlFor="Email" className="form-label">
                        Email
                    </label>
                    <input
                    type={"text"}
                    className="form-control"
                    placeholder="Enter your e-mail address"
                    name="email" 
                    value={email}
                    onChange={(e)=>onInputChange(e)}
                    />
                    
                </div>
                <button type="submit" className="btn btn-outline-primary">
                    Submit
                </button>
                <Link  className="btn btn-outline-danger mx-2" to="/">
                    Cancel
                </Link>
                </form>


            </div>

        </div>
    </div>
  )
}
