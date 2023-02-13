import { Container, Row, Col } from "react-bootstrap"
import React, { useState } from 'react';
import './Main.css';
import { Input } from "../Input";
import { registration } from "../../action/user";
// import classes from './Dialogs.module.css';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { NavLink } from "react-router-dom";
export const Registation = () => {
    const [mail, setMail] = useState('');
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const [phone, setPhone] = useState('');
    const [role, setRole] = useState('');
    function handleSubmit(e) {
        e.preventDefault();

    }
    return (
        <section className="main_page" id="login">
            <Container>
                <Row md={12} xs={12} xl={12} className="logo">
                    <h1 className="name">Farm Helper</h1>
                    <h3 className="slogan">help for your farm</h3>
                </Row>
                <Row md={12} xs={12} xl={12} className="form">
                    <Form onSubmit={handleSubmit}>
                        <Input value={login} setValue={setLogin} id="formPlaintextLogin" name="Login" />
                        <Input value={phone} type="tel" setValue={setPhone} id="formPlaintextPhone" name="Phone" />
                        <Input
                            value={mail} setValue={setMail} id="formPlaintextEmail" type="email" name="email" />
                        <Input value={password} setValue={setPassword} id="formPlaintextPassword" name="Password" />
                        <Input value={role} setValue={setRole} id="formPlaintextPassword" name="Role" />
                        <Col sm={4} xs={12} md={12} className="main_button">
                            <NavLink to='/driver'>
                                <Button variant="primary" type="submit">
                                    Submit
                                </Button>
                            </NavLink>
                            <Button variant="primary" onClick={() => registration(login, phone, mail, password, role)} type="submit">
                                Register
                            </Button>
                        </Col>
                    </Form>
                </Row>
            </Container>
        </section >
    )
}