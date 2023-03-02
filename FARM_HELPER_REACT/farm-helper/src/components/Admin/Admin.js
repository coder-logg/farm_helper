import { Container, Row, Col } from "react-bootstrap"
import '../Profile/Profile.css';
import './Admin.css';
import React, { useState } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import Button from 'react-bootstrap/Button';
import { useNavigate } from 'react-router-dom';
import { NavLink } from "react-router-dom";
export const Admin = () => {
    const { login } = useParams();
    const history = useNavigate();
    const { state } = useLocation();
    const auth = state.auth
    return (
        <section className="main_page" id="login">
            <Container className="container">
                <Row md={12} xs={12} xl={12} className="logo">
                    <div className="ava">
                        <div className="box">
                            <div className="circle">
                                <img src="https://thecatapi.com/api/images/get?format=src&type=png" />
                            </div>
                        </div>
                    </div>
                    <div className="hi">Привет админ {login}</div>
                </Row>
                <Row md={4} xs={2} xl={12} className="buttons">
                    <Col md={4} xs={12} xl={6}>
                        <Button onClick={() => history(`/arbitration/${login}`, { state: { auth: auth } })} variant="secondary" type="submit">
                            Arbitration
                        </Button>
                    </Col>
                    <Col md={4} xs={12} xl={6}>
                        <Button onClick={() => history(`/add_to_db/${login}`, { state: { auth: auth } })} variant="secondary" type="submit">
                            Add to DB
                        </Button>
                    </Col>
                </Row>
            </Container>
        </section >
    )
}