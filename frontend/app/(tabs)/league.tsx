import { View, Text, StyleSheet } from 'react-native';

export default function TeamScreen() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>🏐 Mein Team</Text>
      <Text style={styles.text}>Hier siehst du deine aktuellen Spieler.</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', alignItems: 'center' },
  title: { fontSize: 24, fontWeight: 'bold', marginBottom: 10 },
  text: { fontSize: 16, color: '#555' },
});
