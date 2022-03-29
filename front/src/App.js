import { Form, Input, Button, Row, Col, Space, Descriptions, Radio } from 'antd';
import Paragraph from 'antd/lib/typography/Paragraph';
import Title from 'antd/lib/typography/Title';
import { useState } from 'react';

export const App = () => {

  const [isLoading, setLoading] = useState(false);
  const [response, setResponse] = useState(null);
  const [operacion, setOperacion] = useState(null);
  const [error, setError] = useState(null);

  const onSubmit = (values) => {
    setLoading(true);
    setResponse(null);
    setError(null);
    setOperacion(values.operacion);
    fetch(`/rest/msdxc/${values.operacion}?sueldo=${values.sueldo}&ahorro=${values.ahorro}`)
      .then(response => {
        if (!response.ok) {
          return Promise.reject(response.status);
        }
        return response.json();
      })
      .then(data => {
        console.log(data);
        setResponse(data);
        setLoading(false);
      }).catch(error => {
        console.error(error);
        setError(error);
        setLoading(false);
      });
  };

  return <>
    <Row>
      <Col span={12} offset={6}>
        <Space direction='vertical' style={{ marginTop: 20, width: '100%' }} >
          <Title>Calculadora 10%</Title>
          <Form name="calculadora" layout='vertical'
            onFinish={onSubmit}
            autoComplete="off">

            <Form.Item label="Sueldo" name="sueldo"
              rules={[{ required: true, message: 'Debe ingresar el sueldo' }]} >
              <Input size='large' placeholder='Ingresar Sueldo' type={'number'} />
            </Form.Item>

            <Form.Item label="Ahorro" name="ahorro"
              rules={[{ required: true, message: 'Debe ingresar el ahorro.' }]} >
              <Input size='large' placeholder='Ingresar Ahorro' type={'number'} />
            </Form.Item>

            <Form.Item label="Qué desea hacer?" name="operacion"
              rules={[{ required: true, message: 'Debe seleccionar la operación.' }]}>
                <Radio.Group buttonStyle="outline">
                  <Radio.Button value="dxc">Calcular 10%</Radio.Button>
                  <Radio.Button value="impuesto">Calcular Impuesto</Radio.Button>
                  <Radio.Button value="saldo">Calcular Saldo después del Retiro</Radio.Button>
                  <Radio.Button value="all">Simular Todo</Radio.Button>
                </Radio.Group>
            </Form.Item>
            <Button size='large' type="primary" loading={isLoading} htmlType="submit">Consultar</Button>
          </Form>
        </Space>
      </Col>
      {response && operacion === 'all' ?
        <Col span={12} offset={6}>
          <Space direction='vertical' style={{ marginTop: 20, marginBottom: 20, width: '100%' }} >
            <Descriptions
              bordered
              title="Resultado Simulación"
            >
              <Descriptions.Item label="10 %"><span id="calc_dxc">$ {Intl.NumberFormat('es-CL').format(response.dxc)}</span></Descriptions.Item>
              <Descriptions.Item label="Saldo"><span id="calc_saldo">$ {Intl.NumberFormat('es-CL').format(response.saldo)}</span></Descriptions.Item>
              <Descriptions.Item label="Impuesto"><span id="calc_impuesto">$ {Intl.NumberFormat('es-CL').format(response.impuesto)}</span></Descriptions.Item>
              <Descriptions.Item label="Sueldo"><span id="calc_sueldo">$ {Intl.NumberFormat('es-CL').format(response.sueldo)}</span></Descriptions.Item>
              <Descriptions.Item label="Ahorro"><span id="calc_ahorro">$ {Intl.NumberFormat('es-CL').format(response.ahorro)}</span></Descriptions.Item>
              <Descriptions.Item label="UF"><span id="calc_uf">$ {Intl.NumberFormat('es-CL').format(response.uf)}</span></Descriptions.Item>
            </Descriptions>
          </Space>
        </Col>
        : null}
      {response && operacion === 'dxc' ?
        <Col span={12} offset={6}>
          <Space direction='vertical' style={{ marginTop: 20, marginBottom: 20, width: '100%' }} >
            <Descriptions
              bordered
              title="Resultado Calcular 10%"
            >
              <Descriptions.Item label="10 %"><span id="calc_dxc">$ {Intl.NumberFormat('es-CL').format(response.dxc)}</span></Descriptions.Item>
              <Descriptions.Item label="Sueldo"><span id="calc_sueldo">$ {Intl.NumberFormat('es-CL').format(response.sueldo)}</span></Descriptions.Item>
              <Descriptions.Item label="Ahorro"><span id="calc_ahorro">$ {Intl.NumberFormat('es-CL').format(response.ahorro)}</span></Descriptions.Item>
              <Descriptions.Item label="UF"><span id="calc_uf">$ {Intl.NumberFormat('es-CL').format(response.uf)}</span></Descriptions.Item>
            </Descriptions>
          </Space>
        </Col>
        : null}
      {response && operacion === 'impuesto' ?
        <Col span={12} offset={6}>
          <Space direction='vertical' style={{ marginTop: 20, marginBottom: 20, width: '100%' }} >
            <Descriptions
              bordered
              title="Resultado Calcular Impuesto"
            >
              <Descriptions.Item label="Impuesto"><span id="calc_impuesto">$ {Intl.NumberFormat('es-CL').format(response.impuesto)}</span></Descriptions.Item>
              <Descriptions.Item label="Sueldo"><span id="calc_sueldo">$ {Intl.NumberFormat('es-CL').format(response.sueldo)}</span></Descriptions.Item>
              <Descriptions.Item label="Ahorro"><span id="calc_ahorro">$ {Intl.NumberFormat('es-CL').format(response.ahorro)}</span></Descriptions.Item>
              <Descriptions.Item label="UF"><span id="calc_uf">$ {Intl.NumberFormat('es-CL').format(response.uf)}</span></Descriptions.Item>
            </Descriptions>
          </Space>
        </Col>
        : null}
      {response && operacion === 'saldo' ?
        <Col span={12} offset={6}>
          <Space direction='vertical' style={{ marginTop: 20, marginBottom: 20, width: '100%' }} >
            <Descriptions
              bordered
              title="Resultado Saldo después del Retiro"
            >
              <Descriptions.Item label="Saldo"><span id="calc_saldo">$ {Intl.NumberFormat('es-CL').format(response.saldo)}</span></Descriptions.Item>
              <Descriptions.Item label="Sueldo"><span id="calc_sueldo">$ {Intl.NumberFormat('es-CL').format(response.sueldo)}</span></Descriptions.Item>
              <Descriptions.Item label="Ahorro"><span id="calc_ahorro">$ {Intl.NumberFormat('es-CL').format(response.ahorro)}</span></Descriptions.Item>
              <Descriptions.Item label="UF"><span id="calc_uf">$ {Intl.NumberFormat('es-CL').format(response.uf)}</span></Descriptions.Item>
            </Descriptions>
          </Space>
        </Col>
        : null}
      {error ?
        <Col span={12} offset={6}>
          <Paragraph type='danger'>Ha ocurrido un error al realizar la consulta: {error}</Paragraph>
        </Col>
        : null}
    </Row>
  </>
}