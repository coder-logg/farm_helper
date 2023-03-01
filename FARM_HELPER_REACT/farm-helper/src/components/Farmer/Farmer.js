import { Container, Row, Col } from "react-bootstrap"
import '../Profile/Profile.css';
import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import Button from 'react-bootstrap/Button';
import { NavLink } from "react-router-dom";
import { useNavigate } from 'react-router-dom';
export const Farmer = () => {
    const { login } = useParams();
    const history = useNavigate();
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
                    <div className="hello">Привет фермер {login}</div>
                </Row>
                <Row md={4} xs={2} xl={12} className="buttons">
                    <Col md={4} xs={12} xl={6}>
                        <Button onClick={() => history(`/orders/${login}`)} variant="secondary" type="submit">
                            Orders
                        </Button>
                    </Col>
                    <Col md={4} xs={12} xl={6}>
                        <NavLink to='/farmer'>
                            <Button variant="secondary" type="submit">
                                Your Farm
                            </Button>
                        </NavLink>
                    </Col>
                    <Col md={4} xs={12} xl={6}>
                        <NavLink to='/driver'>
                            <Button variant="secondary" type="submit">
                                Choose driver
                            </Button>
                        </NavLink>
                    </Col>
                    <Col md={4} xs={12} xl={6}>
                        <NavLink to='/reviews'>
                            <Button variant="secondary" type="submit">
                                Reviews
                            </Button>
                        </NavLink>
                    </Col>
                </Row>
            </Container>
        </section >
    )
}