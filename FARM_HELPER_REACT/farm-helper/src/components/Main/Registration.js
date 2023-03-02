import { Container, Row, Col } from "react-bootstrap"
import React, { useState } from 'react';
import './Main.css';
import { Input } from "../Input";
import { registration } from "../../action/user";
// import classes from './Dialogs.module.css';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { useNavigate } from 'react-router-dom';
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
export const Registation = () => {
    const history = useNavigate();
    const [mail, setMail] = useState('');
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const [phone, setPhone] = useState('');
    const [role, setRole] = useState('');
    const roles = ["ADMIN", "FARMER", "DRIVER"]
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
                        Role
                        <DropdownButton value={role} id="dropdown-item-button" title={role ? role : "Choose role"}>
                            {roles.map(
                                (rol) => (
                                    <Dropdown.Item value={rol} onClick={() => setRole(rol)} >{rol}</Dropdown.Item>))}
                        </DropdownButton>
                        <Col sm={12} xs={12} md={12} xl={12} className="main_button">
                            <Button variant="primary" onClick={() => registration(login, phone, mail, password, role, history)} type="submit">
                                Register
                            </Button>
                        </Col>
                    </Form>
                </Row>
            </Container>
        </section >
    )
}