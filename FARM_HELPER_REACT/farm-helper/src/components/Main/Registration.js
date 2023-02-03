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
    const [email, setEmail] = useState("");
    const [login, setLogin] = useState("");
    const [password, setPassword] = useState("");
    return (
        <section className="main_page" id="login">
            <Container>
                <Row md={12} xs={12} xl={12} className="logo">
                    <h1 className="name">Farm Helper</h1>
                    <h3 className="slogan">help for your farm</h3>
                </Row>
                <Row md={12} xs={12} xl={12} className="form">
                    <Form>
                        <Input type="email"
                            value={email} setValue={setEmail} id="formPlaintextEmail" name="Email" />
                        <Input value={login} setValue={setLogin} id="formPlaintextEmail" name="Login" />
                        <Input value={password} setValue={setPassword} id="formPlaintextPassword" name="Password" />
                        <Col sm={4} xs={12} md={12} className="main_button">
                            <NavLink to='/driver'>
                                <Button variant="primary" type="submit">
                                    Submit
                                </Button>
                            </NavLink>
                            <Button variant="primary" onClick={() => registration(email, login, password)} type="submit">
                                Register
                            </Button>
                        </Col>
                    </Form>
                </Row>
            </Container>
        </section >
    )
}