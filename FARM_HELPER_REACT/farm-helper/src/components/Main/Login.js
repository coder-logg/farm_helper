import { Container, Row, Col } from "react-bootstrap"
import './Main.css';
import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { NavLink } from "react-router-dom";
import { Input } from "../Input";
import { Log } from '../../action/user'
import { useNavigate, useLocation } from 'react-router-dom';
export const Login = () => {
    const history = useNavigate();
    const [login, setLogin] = useState("");
    const [password, setPassword] = useState("");
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
                        <Input value={login} setValue={setLogin} id="formPlaintextEmail" name="Login" />
                        <Input value={password} setValue={setPassword} id="formPlaintextPassword" name="Password" />
                        <Col sm={4} xs={12} md={12} className="main_button_log">
                            <Button variant="primary" onClick={() => Log(login, password, history)} type="submit">
                                Submit
                            </Button>
                            <NavLink to='/registration'>
                                <Button variant="primary" type="submit">
                                    Register
                                </Button>
                            </NavLink>
                        </Col>
                    </Form>
                </Row>
            </Container>
        </section >
    )
}